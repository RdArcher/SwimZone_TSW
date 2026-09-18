document.addEventListener("DOMContentLoaded", function() {
    const formCarrelli = document.querySelectorAll(".card-prodotto form");

    formCarrelli.forEach(function(form) {
        form.addEventListener("submit", function(event) {
            event.preventDefault(); 
            
            const formData = new FormData(form);
            formData.append("ajax", "true"); 

            const submitBtn = form.querySelector('button[type="submit"]');
            const originalText = submitBtn.innerHTML;

            submitBtn.innerHTML = "⌛ Attendi...";
            submitBtn.style.pointerEvents = "none";

            fetch(form.action, {
                method: 'POST',
                body: new URLSearchParams(formData) 
            })
            .then(response => {
                if (!response.ok) throw new Error("Errore di rete");
                return response.json(); 
            })
            .then(data => {
                if(data.status === "success") {
                    submitBtn.innerHTML = "Aggiunto!";
                    submitBtn.style.backgroundColor = "#218838";
                    submitBtn.style.color = "white";
                    
                    setTimeout(() => {
                        submitBtn.innerHTML = originalText;
                        submitBtn.style.backgroundColor = "";
                        submitBtn.style.color = "";
                        submitBtn.style.pointerEvents = "auto";
                    }, 2000);
                }
            })
            .catch(error => {
                console.error("Errore AJAX:", error);
                submitBtn.innerHTML = "Errore";
                submitBtn.style.backgroundColor = "#dc3545";
                submitBtn.style.color = "white";
                
                setTimeout(() => {
                    submitBtn.innerHTML = originalText;
                    submitBtn.style.backgroundColor = "";
                    submitBtn.style.color = "";
                    submitBtn.style.pointerEvents = "auto";
                }, 2000);
            });
        });
    });
});
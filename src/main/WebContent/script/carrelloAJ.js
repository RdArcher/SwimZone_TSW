document.addEventListener("DOMContentLoaded", function() {
    const formCarrelli = document.querySelectorAll(".card-prodotto form");

    formCarrelli.forEach(function(form) {
        form.addEventListener("submit", function(event) {
            event.preventDefault();

            const formData = new FormData(form);
            formData.append("ajax", "true");
            const params = new URLSearchParams(formData).toString();

            const submitBtn = form.querySelector('button[type="submit"]');
            const originalText = submitBtn.innerHTML;

            submitBtn.innerHTML = "Attendi";
            submitBtn.style.pointerEvents = "none";

            function ripristinaBottone() {
                setTimeout(() => {
                    submitBtn.innerHTML = originalText;
                    submitBtn.style.backgroundColor = "";
                    submitBtn.style.color = "";
                    submitBtn.style.pointerEvents = "auto";
                }, 2000);
            }

            const request = new XMLHttpRequest();
            request.open("POST", form.action, true);
            request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

            request.onreadystatechange = function() {
                if (this.readyState == 4) {

                    if (this.status == 200) {
                            const data = JSON.parse(this.responseText);

                            if (data.status === "success") {
                                submitBtn.innerHTML = "Aggiunto!";
                                submitBtn.style.backgroundColor = "#218838";
                                submitBtn.style.color = "white";
                            } else {
                                throw new Error("Risposta del server non valida");
                            }

                    } else {
                        if (this.status == 0) {
                            console.error("Errore AJAX: nessuna risposta ricevuta nel tempo limite");
                        } else {
                            console.error("Errore AJAX: " + this.statusText);
                        }
                        submitBtn.innerHTML = "Errore";
                        submitBtn.style.backgroundColor = "#dc3545";
                        submitBtn.style.color = "white";
                    }

                    ripristinaBottone();
                }
            };


            request.send(params);
        });
    });
});
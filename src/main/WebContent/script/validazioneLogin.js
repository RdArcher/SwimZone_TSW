const emailError = "Email non valida";
const passwordError = "Password non valida";
const emptyField = "Campo obbligatorio.";

function validateFormElement(form, span, errorMessage){
	if(form.checkValidity()){
		form.classList.remove("errore");
		span.style.color = "black";
		span.innerHTML="";
		return true;
	} else{
		form.classList.add("errore");
		span.style.color = "red";
		
		if(form.validity.valueMissing){
			span.innerHTML= emptyField;
		} else{
			span.innerHTML = errorMessage;
		}
		
		return false;
	}
}

function validate() {
    let valid = true;	
    
    let form = document.getElementById("formLogin");
    
    let spanEmail = document.getElementById("errorMail");
    if (!validateFormElement(form.email, spanEmail, emailError)){
        valid = false;
    }
    
    let spanPassword = document.getElementById("errorPass");
    if(!validateFormElement(form.password, spanPassword, passwordError)){
        valid = false;
    }
        
    return valid;
}


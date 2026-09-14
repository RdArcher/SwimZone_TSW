const nomeError = "Inserisci un nome valido";
const cognomeError = "Inserisci un cognome valido";
const emailError = "Email non valida";
const passwordError = "Password non valida";
const indirizzoError = "Indirizzo non valido";
const emptyField = "Campo obbligatorio.";

function validateFormElem(formElem, span, errorMessage){
	if(formElem.checkValidity()){
		formElem.classList.remove("error"); 
		span.style.color = "black";
		span.innerHTML = "";
		return true;
	} else {
		formElem.classList.add("error");
		span.style.color = "red";
		
		if(formElem.validity.valueMissing){
			span.innerHTML = emptyField;
		} else {
			span.innerHTML = errorMessage;
		}
		
		return false;
	}
}

function validate(event) {
    let valid = true;	
    
    let nomeInput = document.getElementById("nome");
    let cognomeInput = document.getElementById("cognome");
    let emailInput = document.getElementById("email");
    let passwordInput = document.getElementById("password");
    let indirizzoInput = document.getElementById("indirizzo_spedizione");
    
    let spanNome = document.getElementById("errorNome");
    let spanCognome = document.getElementById("errorCognome");
    let spanEmail = document.getElementById("errorMail");
    let spanPassword = document.getElementById("errorPass");
    let spanIndirizzo = document.getElementById("errorIndirizzo");
    
    if (!validateFormElem(nomeInput, spanNome, nomeError)) valid = false;
    if (!validateFormElem(cognomeInput, spanCognome, cognomeError)) valid = false;
    if (!validateFormElem(emailInput, spanEmail, emailError)) valid = false;
    if (!validateFormElem(passwordInput, spanPassword, passwordError)) valid = false;
    if (!validateFormElem(indirizzoInput, spanIndirizzo, indirizzoError)) valid = false;
    
    if (!valid && event) {
        event.preventDefault();
    }
        
    return valid;
}
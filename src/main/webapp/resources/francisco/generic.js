/* ------------------------------------------------------------------------------------------------------ */
/*                                                                                                        */
/* Pesquisa por CEP                                                                                       */
/*                                                                                                        */
/* ------------------------------------------------------------------------------------------------------ */


var campoCep = document.getElementById('formulario:cep');

$(campoCep).keypress(function(e) {
  	if (e.which == 13 || e.keyCode == 13) {
  		getEndereco();
  	}
});

function getEndereco() {
	var campoCepValue = document.getElementById('formulario:cep').value;
	//var campoCepValue = document.getElementById('[id$=cep]').value;
	campoCepValue = campoCepValue.replace("-", "");
	campoCepValue = campoCepValue.replace(".", "");
	
	if(campoCepValue != ""){
		$.getScript("http://cep.republicavirtual.com.br/web_cep.php?formato=javascript&cep=" + campoCepValue, function() {
			if(resultadoCEP["tipo_logradouro"] != ''){
				if (resultadoCEP["resultado"]) {

        			document.getElementById('formulario:end').value = unescape(resultadoCEP["tipo_logradouro"]).toUpperCase() + " " + unescape(resultadoCEP["logradouro"]).toUpperCase();
        			document.getElementById('formulario:bairro').value = unescape(resultadoCEP["bairro"]).toUpperCase();
        			document.getElementById('formulario:Cidade').value = unescape(resultadoCEP["cidade"]).toUpperCase();
        			document.getElementById('formulario:estado').value = unescape(resultadoCEP["uf"]).toUpperCase();
        			
				}
			} else if (resultadoCEP["cidade"] != ''){
				if (resultadoCEP["resultado"]) {
					
        			document.getElementById('formulario:Cidade').value = unescape(resultadoCEP["cidade"]).toUpperCase();
        			document.getElementById('formulario:estado').value = unescape(resultadoCEP["uf"]).toUpperCase();
        			
				}
			}

	        var campoEndereco = document.getElementsByName("formulario:end");
			$(campoEndereco).focus();
		});
	}
	
}


/* ------------------------------------------------------------------------------------------------------ */
/*                                                                                                        */
/* Previne que o formul�rio seja enviado ao pressionar ENTER nos campos                                   */
/*                                                                                                        */
/* ------------------------------------------------------------------------------------------------------ */


$("form").keypress(function(e) {
	if (e.which == 13) {
		return false;
	}
});

$('form').on('keypress', function(event){
	if(event.which === 13 && $(event.target).is(':input')){
		event.preventDefault();
		//$('#save').trigger('click');
	}
});



/* ------------------------------------------------------------------------------------------------------ */
/*                                                                                                        */
/* UPPERCASE                                                                                              */
/*                                                                                                        */
/* ------------------------------------------------------------------------------------------------------ */


jQuery(document).ready(function(){ 
	
	var comAcentos = ['À', 'Á', 'Â', 'Ã', 'Ä', 'Å', 'Ò', 'Ó', 'Ô', 'Õ', 'Õ', 'Ö', 'Ø', 'È', 'É', 'Ê', 
	                  'Ë', 'Ç', 'Ð', 'Ì', 'Í', 'Î', 'Ï', 'Ù', 'Ú', 'Û', 'Ü', 'Ñ', 'Š', 'Ÿ', 'Ž',
	                  '~'];
	
	var semAcentos = ['A', 'A', 'A', 'A', 'A', 'A', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'E', 'E', 'E', 
	                  'E', 'C', 'D', 'I', 'I', 'I', 'I', 'U', 'U', 'U', 'U', 'N', 'S', 'Y', 'Z',
	                  ''];
	
    jQuery('input').keyup(function() {
    	if (this.value != this.value.toLocaleUpperCase()) {
    		var texto = this.value.toLocaleUpperCase();

    		for (var y = 0; y < comAcentos.length; y++)
    			texto = texto.replace(comAcentos[y], semAcentos[y]);
    		
    		this.value = texto;
    	}
    });
    
    jQuery('textarea').keyup(function() {
    	if (this.value != this.value.toLocaleUpperCase()) {
    		var texto = this.value.toLocaleUpperCase();

    		for (var y = 0; y < comAcentos.length; y++)
    			texto = texto.replace(comAcentos[y], semAcentos[y]);
    		
    		this.value = texto;
    	}
    });
    
});
$("#form-new-usuario").submit(function(evt){
	console.log("CADASTRO NOVO USUARIO");
	evt.preventDefault();
	
	var roles ={};
	roles = $("#roles").val().replace("{","").replace("}","").split(',');
	
	var cadastro = {};
	cadastro.id = $("#id").val();
	cadastro.username = $("#username").val();
	cadastro.password = $("#password").val();
	cadastro.nomeCompleto = $("#nomeCompleto").val();
	
	cadastro.cep = $("#cepNovoUser").val();
	cadastro.logradouro = $("#logradouro").val();
	cadastro.numero = $("#numero").val();
	cadastro.complemento = $("#complemento").val();
	cadastro.bairro = $("#bairro").val();
	cadastro.localidade = $("#localidade").val();
	cadastro.uf = $("#uf").val();
	cadastro.unidade = $("#unidade").val();
	cadastro.ibge = $("#ibge").val();
	cadastro.gia = $("#gia").val();

	cadastro.email = $("#email").val();
	cadastro.telefone = $("#telefone").val();
	cadastro.celular = $("#celular").val();
	
	var rolesUser = {};
	var i;
	
	cadastro.nomeRole = '';
		if($("#Administrador").is(":checked") == true){
			cadastro.nomeRole  += "ROLE_ADMIN,";
		}
		if($("#Usuario").is(":checked") == true){
			cadastro.nomeRole  += "ROLE_USER,";
		}
		if($("#Gerente").is(":checked") == true){
			cadastro.nomeRole  += "ROLE_GERENTE,";
		}
		if($("#Convidado").is(":checked") == true){
			cadastro.nomeRole  += "ROLE_CONVIDADO,";
		}
		if($("#Auxiliar").is(":checked") == true){
			cadastro.nomeRole  += "ROLE_AUXILIAR,";
		}
		
	$.ajax({
		method:"POST",
		url:"/usuario/save",
		data: cadastro,
		beforeSend:function(){
			$("#submeter").addClass("spinner-border spinner-border-sm");
			$("#mensagem").removeClass("alert alert-success");
			$("#mensagem").removeClass("alert alert-danger");
			$("#mensagem").addClass("alert alert-warning").show();
			$("#mensagem").text("Atualizando, espero um momento");
			setTimeout(function(){
				
			},500);
		},
		success:function(result){
				$("#submeter").removeClass("spinner-border spinner-border-sm");
				$("#cep").removeClass("border border-danger");
				$("#password").removeClass("border border-danger");
				$("#nomeCompleto").removeClass("border border-danger");
				$("#email").removeClass("border border-danger");
				$("#celular").removeClass("border border-danger");
				$("#numero").removeClass("border border-danger");
				$("#nomeRole").removeClass("border border-danger");
				
				$("#mensagem").removeClass("alert alert-warning");
				$("#mensagem").addClass("alert alert-success");
				$("#mensagem").text("Usuario Cadastrado").show();
				document.getElementById("form-new-usuario").reset();
		},
		error:function(xhr){
			$("#submeter").removeClass("spinner-border spinner-border-sm");
			console.log("ERROR NOVO USUARIO");
			console.log("error > ", xhr.responseText);
			var errors = $.parseJSON(xhr.responseText);
			$.each(errors,function(key, val){
				console.log("KEY: "+ key + " - VAL: "+val);
				$("#"+ key).addClass("border border-danger");
			});
			$("#mensagem").addClass("alert alert-danger");
			$("#mensagem").show();
			$("#mensagem").text("Erro ao gravar novo usuario");
			alert("Erro ao gravar novo usuario");
			
		}
	});
	
	
});

$("#email").change(function() {
	$("#username").val($("#email").val());
});


$("#cep").change(function() {
  var cep = $(this).val();
  console.log(cep);
  $.ajax({
	  method:"GET",
	  url:"/buscaPorCep/"+cep,
	  beforeSend:function(){
		  $("#statusEndereco").empty();
		  $("#statusEndereco").removeClass("alert alert-warning");
	  },
	  success:function(data){
		  console.log(data);
		  $("#statusEndereco").append("Endereço localizado através do CEP");
		  $("#logradouro").val(data.logradouro);
		  $("#numero").val(data.numero);
		  $("#complemento").val(data.complemento);
		  $("#bairro").val(data.bairro);
		  $("#localidade").val(data.localidade);
		  $("#uf").val(data.uf);
		  $("#cep").val(data.cep);
		  $("#unidade").val(data.unidade);
		  $("#ibge").val(data.ibge);
		  $("#gia").val(data.gia);
	  },
	  error:function(xhr){
		  
		  console.log("ERROR > NÃO FOI POSSIVEL LOCALIZAR O ENDEREÇO ATRAVÉS DO CEP");
		  $("#statusEndereco").append("Não foi possivel localizar o endereço através do CEP");
		  $("#statusEndereco").addClass("alert alert-warning");
	  }
  });
});

$("#meuUsuario").on('click', function(){
	
	var usuario =  document.querySelector("#meuUsuario").href.split("/")[5];	
	
	
	$.ajax({
		method:"GET",
		url:"/usuario/meuUsuario/"+usuario,
		
		cache:false,
		success:function(json){
			
			var usuario = JSON.parse(data);
			console.log("--"+usuario);
			alert(usuario);
			
		},
		error:function(xhr){
			console.log("USUARIO NAO ENCONTRADO, USER: " + usuario + "> ERROR " + xhr.statusText);
			alert("USUARIO NAO ENCONTRADO, USER: " + usuario + "> ERROR " + xhr.statusText);
		}
	})
})
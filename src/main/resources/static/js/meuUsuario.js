$(document).ready(function(){
	$("#mensagem").hide();
});

$("#form-update-meuUsuario").submit(function(evt){
	
	evt.preventDefault();
	
	console.log("ATUALIZAR MEU USUARIO");
	
	var cadastro = {};
	cadastro.id = $("#id").val();
	cadastro.username = $("#username").val();
	cadastro.password = $("#password").val();
	cadastro.nomeCompleto = $("#nomeCompleto").val();
	
	cadastro.cep = $("#cep").val();
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
	
	console.log("+++>", cadastro);
	
	$.ajax({
		method:"POST",
		url:"/usuario/meuUsuario/atualizar",
		data: cadastro,
		beforeSend: function(){
			$("#submeter").addClass("spinner-border spinner-border-sm");
			$("#mensagem").removeClass("alert alert-success");
			$("#mensagem").removeClass("alert alert-danger");
			$("#mensagem").addClass("alert alert-warning");
			$("#mensagem").text("Atualizando, espero um momento").show();
		},
		success:function(data){
			setTimeout(function(){
				
				console.log("DADOS ATUALIZADOS ", data)
				
				$("#nomeCompleto").text(data.nomeCompleto);
				$("#email").text(data.email);
				$("#telefone").text(data.telefone);
				$("#celular").text(data.celular);		
				
				$("#submeter").removeClass("spinner-border spinner-border-sm");	
				$("#mensagem").removeClass("alert alert-warning");
				$("#mensagem").addClass("alert alert-success");
				$("#mensagem").text("Atualizado").show();
			},500);
		},
		error:function(xhr){
			var errors = $.parseJSON(xhr.responseText);
			$.each(errors,function(key, val){
				console.log("KEY: "+ key + " - VAL: "+val);
				$("#"+ key).addClass("border border-danger");
			});
			setTimeout(function(){
				$("#submeter").removeClass("spinner-border spinner-border-sm");	
				$("#mensagem").removeClass("alert alert-warning");
				$("#mensagem").removeClass("alert alert-success");
				$("#mensagem").addClass("alert alert-danger");
				$("#mensagem").text("Não foi possivel atualizar").show();
			},500);
		}
	})
	
	
})
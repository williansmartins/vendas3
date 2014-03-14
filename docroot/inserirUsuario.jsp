<%@ page contentType="text/html; charset=ISO-8859-1" %>

<html> 
	<head>
		<title>Cadastrar Usuario</title>
		<script type="text/javascript">
		<!--
			function setarFoco( campo ) {
				if( document.getElementById("uc54_dscSistemaCaixa").style.display != "none" ) {
					document.formulario.elements[campo].focus();
				}
			}
			
			function setStatus( texto ) {
				window.status=texto;
			}
		
			function validationForm() {
				var login 	  			= document.formulario.login.value;
				var senha 	  			= document.formulario.senha.value;
				var grupo 	  			= document.formulario.grupo.value;
				var perfil 	  			= document.formulario.perfil.value;
				var bloqueado			= document.formulario.bloqueado.value;
				var msgError			= "Campo obrigatório não informado";
				
				if ( login == "" || login <= 0 ) 
				{
					window.alert( msgError );
					document.formulario.login.focus();
					return false;
				}
				if ( senha == "" || senha <= 0 ) 
				{
					window.alert( msgError );
					document.formulario.senha.focus();
					return false;
				}
				if ( grupo == "" || grupo <= 0 ) 
				{
					window.alert( msgError );
					document.formulario.grupo.focus();
					return false;
				}
				if ( perfil == "" || perfil <= 0 ) 
				{
					window.alert( msgError );
					document.formulario.perfil.focus();
					return false;
				}
				else if ( bloqueado == "" ) 
				{
					window.alert( msgError );
					document.formulario.bloqueado.focus();
					return false;
				}
				return true;
			}

			function confirmation() {
				if ( validationForm() ) {
					var answer = confirm("Confirma a inserção ?")
					if ( answer ) {
						document.formulario.submit();
					}
				}	
			}
		//-->
		</script>
	
	</head>
<body>
	<div id="sep_topodestaqueb"><img src="images/space.gif" width="1" height="1" /></div>
	<div id="conteudo-fb">
		<div id="margem">
			<div id="uc54_tela_principal_cadastrar_sistema">		
				<span class="texto_corpo_pequeno">Cadastros  </span><span class="texto_titulos_pequeno">&raquo; Cadastrar Usuário <br />  <br /></span>  <span class="texto_titulos_grande">&raquo; Cadastrar Usuário <br />  <br /></span><span class="texto_titulos_grande"> </span>
				
				<form id="formulario" name="formulario" action="vendas.do?action=InserirUsuario" method="post">
	  				
	  				<table width="95%" border="0" cellspacing="3" cellpadding="0">
	    				<tr>
	      					<td height="20" colspan="2" bgcolor="#8993A7" class="texto_titulos_tabela">&nbsp;&nbsp;<strong>Dados Gerais </strong></td>
	    				</tr>
	    				<tr>
	      					<td width="200" height="20" bgcolor="#F5F5F7" class="texto_corpo"><div align="right"><span class="texto_vermelho">*</span> Login&nbsp;</div></td>
					      	<td bgcolor="#F5F5F7"><input name="login" type="text" class="forms" size="15" maxlength="15" /></td>
					    </tr>
					    <tr>
	      					<td width="200" height="20" bgcolor="#F5F5F7" class="texto_corpo"><div align="right"><span class="texto_vermelho">*</span> Senha&nbsp;</div></td>
					      	<td bgcolor="#F5F5F7"><input name="senha" type="password" class="forms" size="8" maxlength="8" /></td>
					    </tr>
					    <tr>
	      					<td width="200" height="20" bgcolor="#F5F5F7" class="texto_corpo"><div align="right"><span class="texto_vermelho">*</span> Grupo&nbsp;</div></td>
					      	<td bgcolor="#F5F5F7"><input name="grupo" type="text" class="forms" size="20" maxlength="20" /></td>
					    </tr>
					    <tr>
	      					<td width="200" height="20" bgcolor="#F5F5F7" class="texto_corpo"><div align="right"><span class="texto_vermelho">*</span> Perfil&nbsp;</div></td>
					      	<td bgcolor="#F5F5F7"><input name="perfil" type="text" class="forms" size="20" maxlength="20" /></td>
					    </tr>
					    <tr id="uc54_status">
					      	<td width="200" height="20" bgcolor="#F5F5F7" class="texto_corpo"><div align="right"><span class="texto_vermelho">*</span> Bloqueado&nbsp;</div></td>
					      	<td bgcolor="#F5F5F7" class="texto_titulos">
					      		<input name="bloqueado" type="radio" value="S" checked="checked" /> Sim
					        	<input name="bloqueado" type="radio" value="N" /> Não
					        </td>
					    </tr>
				 	</table>
					<table width="95%" border="0" cellpadding="0" cellspacing="3">				    
						<tr id="uc54_Cadastrar">
					    	<td width="200">&nbsp;</td>
					      	<td><input name="Button" type="button" class="texto_corpo" value="Cadastrar" onclick="confirmation()"/></td>
					    </tr>
					    <tr>
					      	<td width="200" height="20">&nbsp;</td>
					      	<td><span class="texto_corpo">(<span class="texto_vermelho">*</span>) Campos de preenchimento obrigat&oacute;rio. </span></td>
					    </tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	
	<div id="menue-fb">
		<div class="paddingwrap">
    		<ul>
      			<li id="mlink"><a href="#"><span>Ajuda</span></a></li>
      			<li id="mlink"><a href="javascript:history.go(-1)"><span>Voltar</span></a></li>
    		</ul>
		</div>
	</div>

</body>
</html>

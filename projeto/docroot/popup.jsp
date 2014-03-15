<%@ page contentType="text/html; charset=ISO-8859-1" %>
<jsp:useBean id="ticket"               scope="session" class="java.lang.String" />

<html>
	<head>
		<title>Resposta</title>
	</head>
	<script language="javascript">
		function alertar(mensagem) 
		{
			window.alert("Nº do ticket: " + mensagem );
  		}
	
	</script>
<body onload="alertar('<%=ticket%>')">
	<div id="sep_topodestaqueb"><img src="images/space.gif" width="1" height="1" /></div>
	<div id="conteudo-fb">
		<div id="margem">
			<b>Ticket gerado: <%=ticket%></b>
		</div>
	</div>
</body>
</html>

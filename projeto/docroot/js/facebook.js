function statusChangeCallback(response) {
	console.log('statusChangeCallback');
	console.log(response);
	// The response object is returned with a status field that lets the
	// app know the current login status of the person.
	// Full docs on the response object can be found in the documentation
	// for FB.getLoginStatus().
	if (response.status === 'connected') {
		// Logged into your app and Facebook.
	} else if (response.status === 'not_authorized') {
		// The person is logged into Facebook, but not your app.
		document.getElementById('status').innerHTML = 'Please log '
				+ 'into this app.';
	} else {
		// The person is not logged into Facebook, so we're not sure if
		// they are logged into this app or not.
		document.getElementById('status').innerHTML = 'Please log '
				+ 'into Facebook.';
	}
}

function checkLoginState() {
	FB.getLoginStatus(function(response) {
		statusChangeCallback(response);
	});
}

window.fbAsyncInit = function() {
	FB.init({
		appId : '237951286409146',
		cookie : true, // enable cookies to allow the server to access the
		// session
		xfbml : true, // parse social plugins on this page
		version : 'v2.0' // use version 2.0
	});

	// Now that we've initialized the JavaScript SDK, we call
	// FB.getLoginStatus(). This function gets the state of the
	// person visiting this page and can return one of three states to
	// the callback you provide. They can be:
	//
	// 1. Logged into your app ('connected')
	// 2. Logged into Facebook, but not your app ('not_authorized')
	// 3. Not logged into Facebook and can't tell if they are logged into
	// your app or not.
	//
	// These three cases are handled in the callback function.

	FB.getLoginStatus(function(response) {
		statusChangeCallback(response);
	});

};

// Load the SDK asynchronously
(function(d, s, id) {
	var js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id))
		return;
	js = d.createElement(s);
	js.id = id;
	js.src = "//connect.facebook.net/en_US/sdk.js";
	fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

function postar(){
	var url = 'http://williansmartins.com/';
	var title = 'Apresentação do Status do Pedido';
	var message = 'Abaixo segue os detalhes de seu pedido \n'
			+ "Data: 02/05/2014 \n" + "Status: Entregue";
	var desc = 'Este é um post informando sobre o status do seu Pedido.';
	var picUrl = 'http://me-siteintegrado.dev.digitalbox.cc/fiap-logo.jpg';

	FB.api('/me/feed', 'post', {
		message : message,
		link : url,
		name : title,
		picture : picUrl,
		description : desc
	}, function(response) {
		console.info(response);
		if (!response || response.error) {
			alert('Error occured...');
		} else {
			alert('Post enviado com o ID: ' + response.id);
		}
	});
}

function postarInserir(tipo){
	var url = 'http://williansmartins.com/';
	var title = 'Apresentação de Controle de Usuario';
	var message = 'Uma ação foi acionada no controle de usuário \n' + 
					'Ação do tipo: ' + tipo + '\n' + 
					'Login: ' + $("#form\\:login").val() + '\n' +
					'Grupo: ' + $("#form\\:grupo").val() + '\n' +
					'Perfil: ' + $("#form\\:perfil").val()  + '\n';
	
	var desc = 'Este é um post informando sobre o controle de usuários.';
	var picUrl = 'http://me-siteintegrado.dev.digitalbox.cc/fiap-logo.jpg';

	FB.api('/me/feed', 'post', {
		message : message,
		link : url,
		name : title,
		picture : picUrl,
		description : desc
	}, function(response) {
		console.info(response);
		if (!response || response.error) {
//			alert('Error occured...' + response.error);
			console.info('Error occured...' + response.error);
		} else {
			alert('Post enviado com o ID: ' + response.id);
		}
	});
}

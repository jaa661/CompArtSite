    $(function() {
    'use strict';

    var client;


    $(function() {
        client = Stomp.over(new SockJS('/socket'));
                client.connect({}, function (frame) {
                    setConnected(true);
                    client.subscribe('/roomId/messages', function (message) {
                    showMessage(JSON.parse(message.body));
                    });
                });
    });

    function showMessage(mesg)
    {
	$('#messages').append('<tr>' +
			      '<td>' + mesg.from + '</td>' +
			      '<td>' + mesg.topic + '</td>' +
			      '<td>' + mesg.message + '</td>' +
			      '<td>' + mesg.time + '</td>' +
			      '</tr>');
    }

    function setConnected(connected) {
	$("#connect").prop("disabled", connected);
	$("#disconnect").prop("disabled", !connected);
	$('#from').prop('disabled', connected);
	$('#text').prop('disabled', !connected);
	if (connected) {
	    $("#conversation").show();
	    $('#text').focus();
	}
	else $("#conversation").hide();
	$("#messages").html("");
    }

    $("form").on('submit', function (e) {
	e.preventDefault();
    });

    $('#from').on('blur change keyup', function(ev) {
	$('#connect').prop('disabled', $(this).val().length == 0 );
    });
    $('#connect,#disconnect,#text').prop('disabled', true);

    $('#connect').click(function() {

    });

    $('#disconnect').click(function() {
	if (client != null) {
	    client.disconnect();
	    setConnected(false);
	}
	client = null;
    });


    $('#send').click(function() {
	var topic = $('#topic').val();
	client.send("/app/chat/" + topic, {}, JSON.stringify({
	    from: usrnme,
	    message: $('#text').val(),
	}));
	$('#text').val("");
    });
});
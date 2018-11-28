    $(function() {
    'use strict';

    var client;


    $(function() {
        client = Stomp.over(new SockJS('/socket'));
                client.connect({}, function (frame) {
                    setConnected(false);
                    /*client.subscribe('/roomId/messages', function (message) {

                    showMessage(JSON.parse(message.body));
                    });*/
                });
    });

    function showMessage(mesg)
    {
	$('#messages').append('<tr>' +
			      '<td>' + mesg.from + '</td>' +
			      '<td>' + mesg.message + '</td>' +
			      '<td>' + mesg.time + '</td>' +
			      '</tr>');
    }

    function setConnected(connected) {
	$("#enterClick").prop("disabled", connected);
	$("#leaveClick").prop("disabled", !connected);
	$('#chatid').prop('disabled', connected);
	$('#text').prop('disabled', !connected);
	$("#conversation").show();

	$("#messages").html("");
    }

    $("form").on('submit', function (e) {
	e.preventDefault();
    });

    $('#chatid').on('blur change keyup', function(ev) {
	$('#enterClick').prop('disabled', $(this).val().length == 0 );
    });
    $('#enterClick,#leaveClick,#text').prop('disabled', true);

    $('#enterClick').click(function() {
        setConnected(true);
        client.subscribe('/roomId/' + $('#chatid').val(), function (message) {

                            showMessage(JSON.parse(message.body));
                            });
    });

    $('#leaveClick').click(function() {
	if (client != null) {
	    setConnected(false);
	    client.unsubscribe('/roomId/' + $('#chatid').val());
	}
    });


    $('#send').click(function() {
	client.send("/app/chat/" + $('#chatid').val(), {}, JSON.stringify({
	    roomId: $('#chatid').val(),
	    from: usrnme,
	    message: $('#text').val(),
	    time: Date().t,
	}));
	$('#text').val("");
    });
});
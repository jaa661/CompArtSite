$('form#postit').submit(
    function(e){
        alert("bite");
        console.log("bite");
        e.preventDefault();
        $.ajax({
            url:'/api/post/add',
            type:'post',
            data:$('#postit').serialize(),
            success:function(){
                alert("worked");
                location = location;
            }
        });
    }
);

//function postit(){
//    $.ajax({
//        url:'/api/post/add',
//        type:'post',
//        data:$('#postit').serialize(),
//        success:function(){
//            alert("worked");
//            location = location;
//        }
//    });
//};
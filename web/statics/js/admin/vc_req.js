/**
 * Created by Vin on 14-5-15.
 */

$(function(){
    bindGitHubSubmit();
    bindBtnClickCss();
});

/**
 * 绑定GitHub按钮的提交事件。在绑定之前先清除按钮其他的Click事件。
 */
function bindGitHubSubmit(){
    var $submit_gh_btn = $("#submit_gh");
    $submit_gh_btn.unbind("click", submit_GitHub_req);
    $submit_gh_btn.bind("click", submit_GitHub_req);
}

var submit_GitHub_req = function(){
    var data = {
        "owner": $("input#owner").val(),
        "repo":$("input#repo").val(),
        "path":$("input#path").val()
    };
    $.ajax({
        url: '/_admin/vcr.req',
        data: data
    }).done(function(msg){
        //alert(msg);
    });
};

function bindBtnClickCss(){
    $("div.btn").on("mousedown", function(){
        $(this).css("background-color", "darkred");
    }).on("mouseup", function(){
        $(this).css("background-color", "");
    })
}
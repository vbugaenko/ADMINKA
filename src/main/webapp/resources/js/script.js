$(document).ready(function () {

    /* $("form#registration select[name='userRole']").change(function () {

         var userRole = $("form#registration select[name='userRole']").val();


         if (parseInt(userRole) == 3) {
             $("form#registration #userAge").fadeIn();
             return false;
         }
         else {
             $("form#registration #userAge").fadeOut();
             return false;
         }
     });*/

    //меню
    $("section .sidebar .open_close").click(function () {
        if ($(this).attr("class") == "open_close") {
            $("section .sidebar").animate({
                width: "+=210"
            }, 1000, function () {
                // Animation complete.
            });
            $(this).attr("class", "open_close close");
            $("section .sidebar a").css({"color": "#2bad4d"});
        }
        else {
            $("section .sidebar").animate({
                width: "-=210"
            }, 1000, function () {
                // Animation complete.
            });
            $(this).attr("class", "open_close");
            $("section .sidebar a").css({"color": "transparent"});
        }
    });

    //завуч. управление группами
    var group = $("form#groups table tbody tr");

    function sendAjaxForGroups(status, groupName, groupId) {

        var object = {status: status, groupName: groupName, groupId: groupId};

        jQuery.ajax(
            {
                url: "/AjaxForGroups/",
                type: "POST",
                data: object,
                success: function (html) {
                    $("#rez").html("<div class='msg success'>" + status + " success</div>");
                }
            });
    }

    $(group).each(function (i) {
        $(group[i]).find("td a.update").click(function () {
            sendAjaxForGroups("update", $(group[i]).find("input[name='group_name']").val(), $(this).attr("data-group-id"));

            return false;
        });
        $(group[i]).find("td a.delete").click(function () {
            sendAjaxForGroups("delete", $(group[i]).find("input[name='group_name']").val(), $(this).attr("data-group-id"));

            $(group[i]).remove();
            return false;
        });
    });
});
$(document).on("click", "#add", function () {
    var id = $("#id").val();
    var title = $("#title").val();
    var professor = $("#professor").val();
    var serializedData = {
        id: id,
        title: title,
        professor: professor
    };

    $.ajax({
        url: "/admin/add",
        type: "POST",
        data: JSON.stringify(serializedData),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function () {
            $('#table tbody').append("<tr><td>" + id + "</td><td>" + title + "</td><td>" + professor + "</td></tr>");
        }
    });
});

$(document).on("click", "#remove", function () {
    var id = $(this).closest("tr");
    var serializedData = {
        id: id.find("td:nth-child(1)").text()
    };

    $.ajax({
        url: "/admin/remove",
        type: "POST",
        data: JSON.stringify(serializedData),
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function () {
            id.fadeOut(500);
        }
    });
});
var cdsFunction = function () {
    $("#buttonSubmit").click(function () {
        $("#formCd").submit();
    });
    $("#formCd").submit(function (event) {
        console.log("Validating form...");
        var numberReg = /^[0-9]+$/;

        var title = $('#title').val();
        if (title == "") {
            $('#title').after('<span class="font_error_bold"> Invalid Title</span>');
            event.preventDefault();
        }

        var artist = $('#artist').val();
        if (artist == "") {
            $('#artist').after('<span class="font_error_bold"> Invalid Artitst</span>');
            event.preventDefault();
        }

        var year = $('#year').val();
        if (year == "") {
            $('#year').after('<span class="font_error_bold"> Invalid Year</span>');
            event.preventDefault();
        }
        if (!numberReg.test(year)) {
            $('#yearL').after('<span class="font_error_bold"> Numbers only</span>');
            event.preventDefault();
        }
    });

    var step = $("#step").val();
    if (step == "cdStored") {
        $("#buttonSubmit").hide();
        $('#title').prop('disabled', true);
        $('#artist').prop('disabled', true);
        $('#year').prop('disabled', true);
    }
};
var h1Text = function () {
    $('h1').click(function () {
        var h1Text = $('h1').text();
        alert(h1Text);
    });
};
var li27TextFunction = function () {
    var li27 = $('#27');
    li27.click(function () {
        var li27Text = li27.text();
        alert(li27Text);
    });
};
var li28TextFunction = function () {
    var domElement = $('.28');
    domElement.click(function () {
        var domElementText = domElement.text();
        alert(domElementText);
    });
};
var createBookFunction = function () {
    var domElement = $("#createBook");
    domElement.click(function () {
        $.ajax({
            type: "POST",
            url: "http://localhost:7070/wildfly10core/createBook",
            data: { author: "Marco Guastalli", title: "Marco IO" }
        })
            .done(function (book) {
                alert("Book created with author: " + book.author + " and title: " + book.title);
            });
    });
};
var displayBookFunction = function () {
    var domElement = $('#displayBook');
    domElement.click(function () {
        $.ajax({
            type: "POST",
            url: "http://localhost:7070/wildfly10core/displayBook",
            data: { author: "Marco Guastalli", title: "Marco IO" }
        })
    });
};
function displayBook(book) {
    var container = $('#books');
    var html = "<span>author: " + book.author + " title: " + book.title + "</span>";
    container.html(html);
}
var displayAllBooksFunction = function () {
    var domElement = $('#displayAllBooks');
    domElement.click(function () {
        $.ajax({
            type: "POST",
            url: "http://localhost:7070/wildfly10core/displayAllBooks"
        })
    });
};
function displayAllBooks(books) {
    var html = "<ul>";
    for (var i = 0; i < books.length; i++) {
        html += "<li>author: " + books[i].author + " title: " + books[i].title + "</li>";
    }
    html += "</ul>";
    $('#books').html(html);
}
var cleanBooksDivFunction = function () {
    var domElement = $('#cleanBooksDiv');
    domElement.click(function () {
        $('#books').html("");
    });
};
var cleanCitiesDivFunction = function () {
    var domElement = $('#cleanCitiesDiv');
    domElement.click(function () {
        $('#cities').html("");
        console.log("cities div clean");
    });
};
var autocompleteDisplayCitiesFunction = function () {
    var domElement = $('#autocompleteDisplayCities');
    domElement.click(function () {
        $.ajax({
            type: "GET",
            url: "http://localhost:7070/wildfly10core/AutoComplete",
            data: {
                searchKey: "Barce",
                productType: "FLIGHT",
                jsonpFunction: "displayCities",
                jsonpInputContainer: "cities",
                departureOrArrival: "DEPARTURE"
            },
            success: function (data, textStatus, jqXHR) {
                console.log("success: " + textStatus);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log('Json error!');
                console.log("jqXHR.status: " + jqXHR.status);
                console.log("jqXHR.statusText: " + jqXHR.statusText);
                console.log("jqXHR.readyState: " + jqXHR.readyState);
                console.log("textStatus: " + textStatus);
                console.log("errorThrown: " + errorThrown);
                console.log("jqXHR.redirect: " + jqXHR.redirect);
            },
            complete: function (jqXHR, textStatus) {
                console.log("complete: " + textStatus);
            }
        })
    });
};
function displayCities(data) {
    var cities = data.cities;
    var html = "<ul>";
    for (var i = 0; i < cities.length; i++) {
        var city = cities[i];
        html += "<li>Name: " + city.cityName + " (" + city.country + ")</li>";
    }
    html += "</ul>";
    $('#cities').html(html);
}
var autocompleteIsJQueryUiAutoCompleteWidgetJsonpSourceFunction = function () {
    var domElement = $('#autocompleteIsJQueryUiAutoCompleteWidgetJsonpSource');
    domElement.click(function () {
        $.ajax({
            type: "GET",
            url: "http://localhost:7070/wildfly10core/AutoComplete",
            data: {
                searchKey: "Madrid",
                productType: "FLIGHT",
                isJQueryUiAutoCompleteWidgetJsonpSource: "true",
                departureOrArrival: "DEPARTURE"
            },
            success: function (data, textStatus, jqXHR) {
                console.log("success: " + textStatus);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log('Json error!');
                console.log("jqXHR.status: " + jqXHR.status);
                console.log("jqXHR.statusText: " + jqXHR.statusText);
                console.log("jqXHR.readyState: " + jqXHR.readyState);
                console.log("textStatus: " + textStatus);
                console.log("errorThrown: " + errorThrown);
                console.log("jqXHR.redirect: " + jqXHR.redirect);
            },
            complete: function (jqXHR, textStatus) {
                console.log("complete: " + textStatus);
            }
        })
    });
}
$(function() {
    $("#city").autocomplete({
        minLength: 3,
        delay: 27,
        source:function(request, response) {
            $.ajax({
                type: "GET",
                url: "http://localhost:7070/wildfly10core/AutoComplete",
                contentType: "application/json",
                dataType: "json",
                data:{
                    searchKey: request.term,
                    productType: "FLIGHT",
                    isJQueryUiAutoCompleteWidgetJsonpSource: "true",
                    departureOrArrival: "DEPARTURE"
                },
                success: function(data, textStatus, jqXHR) {
                    console.log('json success!');
                    response(data);
                    //response($.parseJSON(data));
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log('json error!');
                    console.log("jqXHR.status: " + jqXHR.status);
                    console.log("jqXHR.statusText: " + jqXHR.statusText);
                    console.log("jqXHR.readyState: " + jqXHR.readyState);
                    console.log("textStatus: " + textStatus);
                    //console.log("errorThrown: " + errorThrown);
                    //console.log("jqXHR.redirect: " + jqXHR.redirect);
                },
                complete: function(jqXHR, textStatus) {
                    //console.log("json complete: " + textStatus);
                }
            });
        }
    });
});
/**
 * document ready function
 */
$(document).ready(function () {
    cdsFunction();
    h1Text();
    li27TextFunction();
    li28TextFunction();
    createBookFunction();
    displayBookFunction();
    displayAllBooksFunction();
    cleanBooksDivFunction();
    cleanCitiesDivFunction();
    autocompleteDisplayCitiesFunction();
    autocompleteIsJQueryUiAutoCompleteWidgetJsonpSourceFunction();
});

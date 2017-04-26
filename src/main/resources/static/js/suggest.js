/**
 * Get suggestions from backend
 * @param id
 */
function suggest(id) {

  $.get("/suggest/" + id, function (suggestions) {
    var modal = $("#suggestModal");

    var names = [];

    for (var i = 0; i < suggestions.length; i++) {
      names.push(suggestions[i].name);
    }

    modal.find(".suggested-players").text(names.join(", "));
    modal.modal();
  });
}
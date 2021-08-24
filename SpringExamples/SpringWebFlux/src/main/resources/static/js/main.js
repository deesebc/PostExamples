function loadBooks () {

    this.source = null;

    this.start = function () {

        var bookTable = document.getElementById("books");

        this.source = new EventSource("/books/stream");

        this.source.addEventListener("message", function (event) {

            // These events are JSON, so parsing and DOM fiddling are needed
            var book = JSON.parse(event.data);

            var row = bookTable.getElementsByTagName("tbody")[0].insertRow(0);
            var cell0 = row.insertCell(0);
            var cell1 = row.insertCell(1);
            var cell2 = row.insertCell(2);

            cell0.className = "author-style";
            cell0.innerHTML = book.author;

            cell1.className = "text";
            cell1.innerHTML = book.name;

            cell2.className = "date";
            cell2.innerHTML = book.releaseDate;

        });

        this.source.onerror = function () {
            this.close();
        };

    };

    this.stop = function() {
        this.source.close();
    }

}

book = new loadBooks();

/*
 * Register callbacks for starting and stopping the SSE controller.
 */
window.onload = function() {
    book.start();
};
window.onbeforeunload = function() {
    book.stop();
}
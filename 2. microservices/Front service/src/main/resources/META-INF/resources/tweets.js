function getTweets() {
  const URL = "http://localhost:8080/api/tweets";
  let _table_ = document.createElement("table"),
    _tr_ = document.createElement("tr"),
    _th_ = document.createElement("th"),
    _td_ = document.createElement("td");
  let headers = [];
  ["Content", "Author", "Creation date"].forEach((k) => {
    let th = _th_.cloneNode(false);
    th.appendChild(document.createTextNode(k));
    headers.push(th);
  });
  let table = _table_.cloneNode(false);
  let tr = _tr_.cloneNode(false);
  headers.forEach((th) => tr.appendChild(th));
  table.appendChild(tr);
  fetch(URL, { method: "GET" })
    .then((response) => response.json())
    .then((tweets) => {
      tweets.forEach((t) => {
        let tr = _tr_.cloneNode(false);
        let td = _td_.cloneNode(false);
        td.appendChild(document.createTextNode(t.content));
        tr.appendChild(td);
        td = _td_.cloneNode(false);
        td.appendChild(document.createTextNode(t.author.username));
        tr.appendChild(td);
        td = _td_.cloneNode(false);
        td.appendChild(document.createTextNode(t.createdAt));
        tr.appendChild(td);
        table.appendChild(tr);
      });
      document.getElementById("tweets").appendChild(table);
    });
}

getTweets();

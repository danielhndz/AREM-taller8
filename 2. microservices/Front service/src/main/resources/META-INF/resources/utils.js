const BASE_URL = "http://localhost:8080/";
const AUTH_HEADER = "Authorization";

function getBuiltInRequest(method, url) {
  const XHR = new XMLHttpRequest();
  const authToken = sessionStorage.getItem("auth");
  XHR.open(method, url);
  if (authToken != null) {
    XHR.setRequestHeader(AUTH_HEADER, authToken);
  }
  return XHR;
}

function replaceCurrentDocWithResponse(response) {
  let newDoc = new DOMParser().parseFromString(
    response,
    "text/html"
  ).documentElement;
  let oldDoc = document.documentElement;
  oldDoc.parentNode.replaceChild(newDoc, oldDoc);
}

function onPostTweetClick(tweet) {
  const XHR = getBuiltInRequest("POST", BASE_URL + "api/tweets");
  const FD = new FormData();
  FD.append("tweet", tweet);
  XHR.addEventListener("load", () => {
    if (XHR.status == 200) {
      alert("Tweet posteado correctamente!");
      onStreamClick();
    }
  });
  XHR.send(FD);
}

function onTweetClick() {
  const XHR = getBuiltInRequest("GET", BASE_URL + "tweet");
  XHR.addEventListener("load", () => {
    if (XHR.status == 200) {
      replaceCurrentDocWithResponse(XHR.response);
    }
  });
  XHR.send();
}

function onStreamClick() {
  const XHR = new XMLHttpRequest();
  XHR.addEventListener("load", () => {
    if (XHR.status == 200) {
      replaceCurrentDocWithResponse(XHR.response);
      getTweets();
    }
  });
  XHR.open("GET", BASE_URL);
  XHR.send();
}

function onLoginClick(params) {
  const URL = BASE_URL + "api/users/login";
  const XHR = new XMLHttpRequest();
  const FD = new FormData();
  FD.append("username", params.username);
  FD.append("passwd", params.passwd);
  XHR.addEventListener("load", () => {
    if (XHR.status == 200) {
      sessionStorage.setItem("auth", XHR.response);
      onTweetClick();
    } else if (XHR.status == 401) {
      alert("Tus credenciales no son correctas");
    }
  });
  XHR.addEventListener("error", () => {
    alert("error");
  });
  XHR.open("POST", URL);
  XHR.send(FD);
}

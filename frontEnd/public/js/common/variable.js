// fetch URL
const BASE_COMMON_URL = "http://localhost:8080/api/v1";
const BASE_COOKIE_URL = "http://localhost:8080/api/v2"

// fetch method
const METHOD = {
    POST: "POST", 
    GET: "GET"
}

// fetch header
const HEADER = {
    POST : { 'Content-Type': 'application/json' },
}

export {
    // fetch URL
    BASE_COMMON_URL, BASE_COOKIE_URL,

    // fetch method
    METHOD,

    // fetch header
    HEADER
}
// fetch URL
const BASE_COMMON_URL = "http://localhost:8080/api/v1";
const BASE_COOKIE_URL = "http://localhost:8080/api/v2"

// release fetch URL
const RELEASE_HOST_URL = "www.oilpocket.kro.kr";
const RELEASE_COMMON_URL = "https://www.oilpocket.kro.kr/api/v1";
const RELEASE_COOKIE_URL = "https://www.oilpocket.kro.kr/api/v2";

// fetch method
const METHOD = {
    POST: "POST", 
    GET: "GET",
    PATCH: "PATCH"
}

// fetch header
const HEADER = {
    POST : { 'Content-Type': 'application/json' },
    PATCH : { 'Content-Type': 'application/json' }
}

export {
    // fetch URL
    BASE_COMMON_URL, BASE_COOKIE_URL,
    
    // release fetch URL
    RELEASE_HOST_URL, RELEASE_COMMON_URL, RELEASE_COOKIE_URL,

    // fetch method
    METHOD,

    // fetch header
    HEADER
}
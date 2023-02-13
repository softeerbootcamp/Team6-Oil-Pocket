import { getNavBarTemplate } from "./template.js";

const navBarView = () => {
    const $navBar = document.createElement("section");
    $navBar.classList.add("navbar");
    $navBar.innerHTML = getNavBarTemplate();

    return $navBar;
}

export { navBarView }
import { getNavBarTemplate } from "./template";

const navBarView = () => {
    const $navBar = document.createElement("section");
    $navBar.classList.add("navbar");

    $navBar.innerHTML = getNavBarTemplate();

    return $navBar;
}

export { navBarView }
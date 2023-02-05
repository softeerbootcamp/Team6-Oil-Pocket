import { navBarView } from "../navbar/view.js";
import { getMainViewContentTemplate } from "./template.js";

const mainView = () => {
    const $mainViewContainer = document.createElement("section");
    const $mainViewContent = document.createElement("section");

    $mainViewContent.innerHTML = getMainViewContentTemplate();

    $mainViewContainer.appendChild(navBarView());
    $mainViewContainer.appendChild($mainViewContent);

    return $mainViewContainer;
}

export { mainView }
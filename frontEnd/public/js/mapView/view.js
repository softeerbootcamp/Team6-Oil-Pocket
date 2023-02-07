import { navBarView } from "../navbar/view";
import { getMapViewContentTemplate } from "./template";

const mapView = () => {
    const $mapViewContainer = document.createElement("section");
    const $mapViewContent = document.createElement("section");
    $mapViewContent.classList.add("main");

    $mapViewContent.innerHTML = getMapViewContentTemplate();

    $mapViewContainer.appendChild(navBarView());
    $mapViewContainer.appendChild($mapViewContent);

    return $mapViewContainer;
}

export { mapView }
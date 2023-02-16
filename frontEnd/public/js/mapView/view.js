import { navBarView } from "../navbar/view";
import { initTmap } from "./helperFunction";
import { getMapContentTemplate } from "./template";


const mapView = async () => {
    const $mapViewContainer = document.createElement("section");
    const $mapContentcontainer = document.createElement("section");

    $mapContentcontainer.classList.add("main");

    $mapViewContainer.appendChild(navBarView());
    $mapViewContainer.appendChild($mapContentcontainer);

    $mapContentcontainer.innerHTML = getMapContentTemplate();

    return $mapViewContainer;
}

export { mapView }
import { navBarView } from "../navbar/view";
import { eventToSeekBarBtns } from "./event";
import { getMapContentTemplate } from "./template";


const mapView = async () => {
    const $mapViewContainer = document.createElement("section");
    const $mapContentcontainer = document.createElement("section");

    $mapContentcontainer.classList.add("main");

    $mapViewContainer.appendChild(navBarView());
    $mapViewContainer.appendChild($mapContentcontainer);

    $mapContentcontainer.innerHTML = getMapContentTemplate();
    
    eventToSeekBarBtns($mapViewContainer);

    return $mapViewContainer;
}

export { mapView }
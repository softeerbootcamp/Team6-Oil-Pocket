import { navBarView } from "../../navbar/view.js";
import { eventToOilPriceInput, eventToOilSelectArea, eventToOilSearchInput, 
    eventToRegisterBtn, eventToSearchValue, eventToPreferBtn, eventToPreferModalCloseBtn 
} from "./event.js";
import { getInputOilInfoTemplate, getRecentGasStationRow, getSearchTemplate } from "./template.js";

const inputOilInfoView = async () => {
    const $inputOilInputContainer = document.createElement("section");
    const $inputOilInputContent = document.createElement("section");
    $inputOilInputContent.classList.add("main");

    $inputOilInputContent.innerHTML = getInputOilInfoTemplate();

    $inputOilInputContainer.appendChild(navBarView());
    $inputOilInputContainer.appendChild($inputOilInputContent);

    eventToOilSelectArea($inputOilInputContent);
    eventToOilPriceInput($inputOilInputContent);
    eventToOilSearchInput($inputOilInputContent);
    eventToRegisterBtn($inputOilInputContent);
    eventToPreferBtn($inputOilInputContent);
    eventToPreferModalCloseBtn($inputOilInputContent)

    return $inputOilInputContainer;
}

const gasStationSearchView = (stationName, address, stationNo, brandImageURL) => {
    const $gasSearchSection = document.createElement("section");
    $gasSearchSection.classList.add("oilInfoArea__oilSearchValue");
    $gasSearchSection.dataset.stationNo = stationNo;
    $gasSearchSection.innerHTML = getSearchTemplate(stationName, address, brandImageURL);

    eventToSearchValue($gasSearchSection);

    return $gasSearchSection;
}

const recentGasStationView = (brandURL, gasStationName, location) => {
    const $recentRow = document.createElement("li");
    $recentRow.innerHTML = getRecentGasStationRow(brandURL, gasStationName, location);

    return  $recentRow;
}

export { inputOilInfoView, gasStationSearchView, recentGasStationView }
import { navBarView } from "../../navbar/view.js";
import { getChartTemplate } from "./template.js";

const chartView = () => {
    const $chartContainer = document.createElement("section");
    const $chartContent = document.createElement("section");
    $chartContent.classList.add("main");

    $chartContent.innerHTML = getChartTemplate();

    $chartContainer.appendChild(navBarView());
    $chartContainer.appendChild($chartContent);

    return $chartContainer;
}

export { chartView }

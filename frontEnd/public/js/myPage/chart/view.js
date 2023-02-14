import { navBarView } from "../../navbar/view.js";
import { eventToChartLegend } from "./event.js";
import { makeChart } from "./helperFunction.js";
import { getChartTemplate } from "./template.js";

const chartView = async () => {
    const $chartContainer = document.createElement("section");
    const $chartContent = document.createElement("section");
    $chartContent.classList.add("main");

    $chartContent.innerHTML = getChartTemplate();

    $chartContainer.appendChild(navBarView());
    $chartContainer.appendChild($chartContent);

    let userOilArray = [10_000, 20_000, 30_000, 40_000, 50_000, 60_000, 70_000, 80_000, 90_000, 100_000];
    let commonOilArray = [100_000, 90_000, 80_000, 70_000, 60_000, 50_000, 40_000, 30_000, 20_000, 10_000];

    makeChart($chartContent, userOilArray, commonOilArray);
    eventToChartLegend($chartContent);

    return $chartContainer;
}

export { chartView }

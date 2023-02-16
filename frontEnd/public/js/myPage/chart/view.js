import { navBarView } from "../../navbar/view.js";
import { eventToChartLegend } from "./event.js";
import { fetchChart } from "./fetch.js";
import { makeChart } from "./helperFunction.js";
import { getChartTemplate } from "./template.js";

let userOilArray = [10_000, 20_000, 30_000, 90_000, 100_000, 80_000, 85_000, 80_000, 70_000, 105_000, 0, 0];
let commonOilArray = [100_000, 90_000, 80_000, 70_000, 60_000, 40_000, 89_000, 73_000, 86_000, 90_000, 0, 0];

const chartView = async () => {
    const $chartContainer = document.createElement("section");
    const $chartContent = document.createElement("section");
    $chartContent.classList.add("main");

    $chartContent.innerHTML = getChartTemplate();

    $chartContainer.appendChild(navBarView());
    $chartContainer.appendChild($chartContent);

    await fetchChart();

    makeChart($chartContent, userOilArray, commonOilArray);
    eventToChartLegend($chartContent);

    return $chartContainer;
}

export { userOilArray, commonOilArray, chartView }
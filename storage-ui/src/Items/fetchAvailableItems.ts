import {AvailableItems} from "./types/AvailableItems";

const HTTP_STATUS_OK = 200;

export const fetchAvailableItems = async (baseUrl: string): Promise<AvailableItems> => {
    const requestUrl = buildRequestUrl(baseUrl);
    const response = await fetch(requestUrl);
    if (response.status === HTTP_STATUS_OK) {
        return await response.json() as AvailableItems
    } else {
        throw new Error(`fetching items failed with status ${response.status}`)
    }
}

const buildRequestUrl = (baseUrl: string): string  => {
    return `${baseUrl}/items`
}
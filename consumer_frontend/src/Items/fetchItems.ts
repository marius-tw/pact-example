interface Item {}
type Items = Item[]
const HTTP_STATUS_OK = 200;

export const fetchItems = async (baseUrl: string): Promise<Items> => {
    const requestUrl = buildRequestUrl(baseUrl);
    const response = await fetch(requestUrl);
    if (response.status === HTTP_STATUS_OK) {
        return await response.json() as Items
    } else {
        throw new Error(`fetching items failed with status ${response.status}`)
    }
}

const buildRequestUrl = (baseUrl: string): string  => {
    return `${baseUrl}/items`
}
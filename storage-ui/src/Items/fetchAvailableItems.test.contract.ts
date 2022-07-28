/**
 * @jest-environment jsdom
 */
import {createContract} from "../test-utils";
import {like} from "@pact-foundation/pact/src/dsl/matchers";
import {fetchAvailableItems} from "./fetchAvailableItems";
import {AvailableItems} from "./types/AvailableItems";

describe('fetchAvailableItems', () => {

    const contract = createContract({
        consumerName: "consumer-frontend",
        providerName: "provider",
    })

    const baseUrl = `http://localhost:${contract.opts.port}`

    it('GET /items-available from storage-service', async () => {
        //given
        const expectedAvailableItems: AvailableItems = [
            {name: "Item1"},
            {name: "Item2"},
            {name: "Item3"},
        ]
        await contract.addInteraction({
            state: "returns all available items",
            uponReceiving: "returns all available items",
            withRequest: {
                path: '/items-available',
                method: "GET"
            },
            willRespondWith: {
                status: 200,
                headers: {
                    "Content-Type": "application-json"
                },
                body: like(expectedAvailableItems)
            }
        })
        //when
        const actualAvailableItems = await fetchAvailableItems(baseUrl)
        //then
        expect(actualAvailableItems).toEqual(expectedAvailableItems)
    });
});
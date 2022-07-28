import * as React from "react";
import {useEffect, useState} from "react";
import { AvailableItems as ItemsType, AvailableItem as ItemType} from './types/AvailableItems'
import {fetchAvailableItems} from "./fetchItems";

const BASE_BACKEND_URL = "http://localhost:8080"

export const Items = () => {
    const [items, setItems] = useState<ItemsType | undefined>(undefined)
    const [hasError, setError] = useState<boolean>(false)

    useEffect(() => {
        fetchAvailableItems(BASE_BACKEND_URL)
            .then((items) => setItems(items))
            .catch((err) => {
                console.error(err)
                setError(true)
            })
    }, [])

    if (hasError) {
        return <span>Could not fetch available items</span>
    }

    if (items === undefined) {
        return <span>Loading...</span>
    }

    return (<>
        <h2>Available items</h2>
        {items.map(item => <Item item={item}/> )}
    </>)
};

interface Props {
    item: ItemType
}

const Item = ({item}: Props) => {
    return <div>
        I'm an item
    </div>
}
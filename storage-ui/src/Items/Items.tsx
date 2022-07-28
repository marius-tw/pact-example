import * as React from "react";
import {useEffect, useState} from "react";
import { AvailableItems as ItemsType, AvailableItem as ItemType} from './types/AvailableItems'
import {fetchAvailableItems} from "./fetchAvailableItems";
import {Card, CardContent, CardHeader, Typography} from "@mui/material";

const BASE_BACKEND_URL = "http://localhost:8081"

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

    return (
        <div style={{ display: "flex"}}>
        {items.map(item => <Item item={item}/> )}
        </div>
    )
};

interface Props {
    item: ItemType
}

const Item = ({item}: Props) => {
    return <Card style={{margin: 8}}>
        <CardHeader title={item.name} />
        <CardContent>
            <Typography variant="body1" component="p">
        A perfect item that's stored under the name: {item.name}
            </Typography>
        </CardContent>
    </Card>
}
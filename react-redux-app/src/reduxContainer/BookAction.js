import { buy_book, sell_book } from "./BookType"

export const purchase_book = () => {
    console.log("Purchase book action created....")
    return {
        type: buy_book,
        payload: "I am buying book"
    }
}

export const selling_book = () => {
    console.log("Selling book action created....")
    return {
        type: sell_book,
        payload: "I am selling book"
    }
}
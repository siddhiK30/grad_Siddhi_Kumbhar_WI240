import { buy_book, sell_book } from "./BookType"

const initialState = {
    NumOfBooks: 2000
}

const BookReducer = (state = initialState, action) => {
    console.log("Action : " + action.type)

    switch (action.type) {
        case buy_book: 
            return {
                ...state,
                NumOfBooks: state.NumOfBooks - 1
            }

        case sell_book: 
            return {
                ...state,
                NumOfBooks: state.NumOfBooks + 1
            }

        default: 
            return state
    }
}

export default BookReducer
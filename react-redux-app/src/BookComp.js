import React from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { purchase_book, selling_book } from './reduxContainer/BookAction'

function BookComp() {

    const noOfBooks = useSelector(state => state.NumOfBooks)

    const dispatch = useDispatch()

    return (
        <div>
            <h2>This is from Book Store Component</h2>

            <h3>No. of Books : { noOfBooks }</h3>

            <button onClick={() => dispatch(purchase_book())}>
                BUY BOOK
            </button>

            <button onClick={() => dispatch(selling_book())}>
                SELL BOOK
            </button>
        </div>
    )
}

export default BookComp;
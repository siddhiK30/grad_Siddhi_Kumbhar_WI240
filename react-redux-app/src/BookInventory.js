import React from 'react'
import { useDispatch, useSelector } from 'react-redux'


function BookInventory() {

    const noOfBooks = useSelector(state => state.NumOfBooks)

    const dispatch = useDispatch()

    return (
        <div>
           

            <h3>No. of Books in inventory : { noOfBooks }</h3>

           
        </div>
    )
}

export default BookInventory;
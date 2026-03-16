import React, { useState } from 'react'
import { Link } from "react-router-dom"

function Deposit() {

  const [amount, setAmount] = useState("")
  const [interest, setInterest] = useState("")
  const [time, setTime] = useState("")
  const [result, setResult] = useState("")

  const calculateDeposit = () => {

    let P = Number(amount)
    let r = Number(interest) / 100
    let t = Number(time)

    let maturity = P * (1 + r * t)

    setResult(maturity.toFixed(2))
  }

  const container = {
    display: "flex",
    justifyContent: "center",
    marginTop: "50px"
  }

  const card = {
    border: "1px solid #ddd",
    borderRadius: "10px",
    padding: "30px",
    width: "350px",
    boxShadow: "0px 4px 10px rgba(0,0,0,0.1)"
  }

  const input = {
    width: "100%",
    padding: "8px",
    marginTop: "5px"
  }

  const button = {
    marginTop: "15px",
    padding: "10px",
    width: "100%",
    background: "green",
    color: "white",
    border: "none",
    borderRadius: "5px"
  }

  return (
    <div style={container}>

      <div style={card}>

        <h2 style={{textAlign:"center"}}>Deposit Calculator</h2>

        Deposit Amount:
        <input
          type="number"
          style={input}
          onChange={(e)=>setAmount(e.target.value)}
        />

        <br /><br />

        Interest Rate (%):
        <input
          type="number"
          style={input}
          onChange={(e)=>setInterest(e.target.value)}
        />

        <br /><br />

        Duration (Years):
        <input
          type="number"
          style={input}
          onChange={(e)=>setTime(e.target.value)}
        />

        <button style={button} onClick={calculateDeposit}>
          CALCULATE
        </button>

        <h3 style={{textAlign:"center"}}>
          Maturity Amount: {result}
        </h3>

        <div style={{textAlign:"center"}}>
          <Link to="/">⬅ Back</Link>
        </div>

      </div>

    </div>
  )
}

export default Deposit
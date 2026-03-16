import React, { useState, useEffect } from 'react'
import { Link } from "react-router-dom"

function Home() {

  const [loanType, setLoanType] = useState("")
  const [interest, setInterest] = useState(0)
  const [minAmount, setMinAmount] = useState(0)
  const [maxDuration, setMaxDuration] = useState(0)
  const [amount, setAmount] = useState("")
  const [duration, setDuration] = useState("")
  const [emi, setEmi] = useState("")

  useEffect(() => {
    setLoanType("home")
    setInterest(7)
    setMinAmount(500000)
    setMaxDuration(30)
  }, [])

  const calculateEMI = () => {

    if (amount < minAmount) {
      alert("Enter amount greater than minimum amount")
      return
    }

    if (duration > maxDuration) {
      alert("Duration exceeds maximum limit")
      return
    }

    let r = interest / (12 * 100)
    let n = duration * 12

    let emiValue =
      (amount * r * Math.pow(1 + r, n)) /
      (Math.pow(1 + r, n) - 1)

    setEmi(emiValue.toFixed(2))
  }

  const container = {
    display: "flex",
    justifyContent: "center",
    marginTop: "40px"
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
    background: "#007BFF",
    color: "white",
    border: "none",
    borderRadius: "5px"
  }

  return (
    <div style={container}>

      <div style={card}>

        <h2 style={{textAlign:"center"}}>Home Loan EMI Calculator</h2>

        Applicant:
        <input type="text" style={input} />

        <br /><br />

        <b>Interest:</b> {interest}% <br />
        <b>Min Amount:</b> {minAmount} <br />
        <b>Max Duration:</b> {maxDuration} Years

        <br /><br />

        Enter Amount:
        <input
          type="number"
          style={input}
          onChange={(e) => setAmount(Number(e.target.value))}
        />

        <br />

        Enter Duration (Years):
        <input
          type="number"
          style={input}
          onChange={(e) => setDuration(Number(e.target.value))}
        />

        <button style={button} onClick={calculateEMI}>
          CALCULATE EMI
        </button>

        <h3 style={{textAlign:"center"}}>EMI: {emi}</h3>

        <div style={{textAlign:"center"}}>
          <Link to="/">⬅ Back</Link>
        </div>

      </div>

    </div>
  )
}

export default Home
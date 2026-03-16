import logo from './logo.svg';
import './App.css';

import Loan from './Loan';
import Car from './Car';
import Personal from './Personal';
import Home from './Home';
import Deposit from './Deposit';

import { BrowserRouter, Routes, Route } from "react-router-dom";
import Land from './Land';

function App() {
  return (
    <div className="App">

      <BrowserRouter>

        <Routes>
          <Route path="/" element={<Land />} />
          <Route path="/home" element={<Home />} />
          <Route path="/car" element={<Car />} />
          <Route path="/personal" element={<Personal />} />
          <Route path="/deposit" element={<Deposit />} />
        </Routes>

      </BrowserRouter>

    </div>
  );
}

export default App;
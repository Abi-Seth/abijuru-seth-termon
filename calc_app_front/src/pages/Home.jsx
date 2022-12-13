import React, { useState, useEffect, useReducer } from "react";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import copy from "copy-to-clipboard";
import axios from "axios";

function Home() {
  const [opnd1, setopnd1] = useState(0);
  const [opnd2, setopnd2] = useState(0);
  const [opt, setopt] = useState("");
  const [dialog, setdialog] = useState(false);
  const [result, setresult] = useState("00.00");

  const calculateAnswer = (event) => {
    event.preventDefault();

    let headers = new Headers();

    headers.append('Content-Type', 'application/json');
    headers.append('Accept', 'application/json');

    headers.append('Access-Control-Allow-Origin', 'http://localhost:3000');
    headers.append('Access-Control-Allow-Credentials', 'true');

    headers.append('GET', 'POST', 'OPTIONS');
    axios.post('http://127.0.0.1:8080/api/v1/doMath', {
      operand1: opnd1,
      operand2: opnd2,
      operation: opt
    }, headers)
      .then((res) => {
        setresult(res.calcResponse)
        setdialog(true)
      })
      .catch(err => {
        toast.error("Invalid Operation!")
      })
  }

  const copyToClipboard = () => {
    copy(`${result}`)
  }

  return (
    <div className="max-w-2xl py-10 mt-5 md:mt-15 mx-auto">
      <div
        className="max-w-md mx-auto p-3 mb-3 rounded-lg text-center "
      >
        <p className="title text-lg font-bold md:text-4xl">
          <span className="md:text-6xl"> 🔫 </span><br /> Calculating Gun
        </p>
        <br />
        <p className="sub-title text-lg opacity-30 md:text-2xl">
          A calculator with no limits{" "}
        </p>
      </div>
      <ToastContainer
        position="top-center"
        autoClose={5000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
      />

      {dialog ? (
        <>
          <div className=" flex justify-between rounded-md relative bg-accent max-w-md mx-3 sm:mx-auto mb-5 p-5">
            <div>
              <div className="mb-3 text-neutral  relative">
                <div className="text-2xl ">RESULT:</div>
                <div className="text-lg">
                  <li className="result">
                    {result}
                  </li>
                </div>
              </div>
              <button
                onClick={copyToClipboard}
                className="btn btn-primary mr-2"
              >
                Copy
              </button>
            </div>
            <button
              onClick={() => setdialog(false)}
              className="btn btn-sm btn-circle btn-ghost "
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                className="inline-block w-6 h-6 stroke-current"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth={2}
                  d="M6 18L18 6M6 6l12 12"
                ></path>
              </svg>
            </button>
          </div>
        </>
      ) : (
        <>
          {" "}
          <h1></h1>
        </>
      )}
      <form
        className="form-control mx-2 max-w-lg sm:mx-auto bg-base-300 p-5 rounded-md"
        onSubmit={calculateAnswer}
      >
        <label class="label">
          <span class="label-text text-lg ">Operand One </span>
        </label>
        <input
          className="input input-accent input-bordered mb-5"
          value={opnd1}
          required
          type="number"
          placeholder="00.0"
          onChange={(e) => setopnd1(e.target.value)}
        ></input>
        <label class="label">
          <span class="label-text text-lg ">Operand Two </span>
        </label>
        <input
          className="input input-accent input-bordered mb-5"
          value={opnd2}
          required
          type="number"
          placeholder="00.0"
          onChange={(e) => setopnd2(e.target.value)}
        ></input>
        <div className=" p-5 card bg-base-200">
          <p className=" text-lg"></p>
          <label className="label">
            <span className="label-text">Operation: </span>
          </label>
          <input
            className="input input-bordered mb-5"
            value={opt}
            placeholder="Enter your operation (ln, log, +, - , /)"
            type="text"
            required
            onChange={(e) => setopt(e.target.value)}
          ></input>
        </div>
        <div className="divider"></div>
        <button className="btn btn-primary" type="submit">
          Calculate RESULT
        </button>
      </form>
    </div>
  );
}

export default Home;

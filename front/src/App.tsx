import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import ManuscriptEditor from './features/Manuscript/ManuscriptEditor';

function App() {
  const [count, setCount] = useState(0)

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<ManuscriptEditor />} />
        <Route path="/manuscript" element={<ManuscriptEditor />} />
      </Routes>

    </BrowserRouter>
  )
}

export default App

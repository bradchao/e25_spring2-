import {Routes, Route} from 'react-router'
import Page1 from './pages/brad01.tsx'
import Page2 from './pages/brad02.tsx'

function App(){
    return (
        <div>
            <Routes>
                <Route
                    path="/page1"
                    element={<Page1 />}
                />
            </Routes>
            <Routes>
                <Route
                    path="/page2"
                    element={<Page2 />}
                />
            </Routes>
        </div>
    )
}
export default App
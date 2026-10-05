import {Routes, Route} from 'react-router'
import Page1 from './pages/brad01.tsx'
import Page2 from './pages/brad02.tsx'
import Page3 from './pages/brad03.tsx'
import Page4 from './pages/brad04.tsx'
import Page5 from './pages/brad05.tsx'

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
            <Routes>
                <Route
                    path="/page3"
                    element={<Page3 />}
                />
            </Routes>
            <Routes>
                <Route
                    path="/page4"
                    element={<Page4 />}
                />
            </Routes>
            <Routes>
                <Route
                    path="/page5"
                    element={<Page5 />}
                />
            </Routes>
        </div>
    )
}
export default App
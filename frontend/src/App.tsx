import {Routes, Route} from 'react-router'
import Page1 from './pages/brad01.tsx'
import Page2 from './pages/brad02.tsx'
import Page3 from './pages/brad03.tsx'
import Page4 from './pages/brad04.tsx'
import Page5 from './pages/brad05.tsx'
import Page6 from './pages/brad06.tsx'
import Page7 from './pages/brad07.tsx'
import Page8 from './pages/brad08.tsx'
import Page9 from './pages/brad09.tsx'

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
            <Routes>
                <Route
                    path="/page6"
                    element={<Page6 />}
                />
            </Routes>
            <Routes>
                <Route
                    path="/page7"
                    element={<Page7 />}
                />
            </Routes>
            <Routes>
                <Route
                    path="/page8"
                    element={<Page8 />}
                />
            </Routes>
            <Routes>
                <Route
                    path="/page9"
                    element={<Page9 />}
                />
            </Routes>

        </div>
    )
}
export default App
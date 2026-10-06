import type {Member} from "../types/Member.ts";

export default function HomePage(){
    const memberJson = localStorage.getItem("member");
    const member: Member | null = memberJson? JSON.parse(memberJson):null;
    if (!member) return (<p>Login First</p>);



    return (
        <main>
            <h1>Home Page</h1>
            <hr></hr>
            <h2>Welcome, {member.name}</h2>


        </main>
    );
}
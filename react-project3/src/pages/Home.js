import Button from "../components/Button";

const Home = () => {
    return (
        <div>
            <Button
                text={"버튼 텍스트"}
                onClick={() => {
                    alert("hi");
                }} />
        </div>
    );
};

export default Home;
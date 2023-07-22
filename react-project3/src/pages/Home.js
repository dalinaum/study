import Button from "../components/Button";
import Header from "../components/Header";

const Home = () => {
    return (
        <div>
            <Header
                title={"Home"}
                leftChild={
                    <Button
                        type="positive"
                        text={"긍정 버튼"}
                        onClick={() => {
                            alert("positive button");
                        }}
                    />
                }
                rightChild={
                    <Button
                        type="negative"
                        text={"부정s 버튼"}
                        onClick={() => {
                            alert("negative button");
                        }}
                    />
                }
            />
        </div>
    );
};

export default Home;
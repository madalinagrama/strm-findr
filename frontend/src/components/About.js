import React from "react";

import { VscGithubInverted } from "react-icons/vsc";

const About = () => {
    return (
        <div>
            <br />
            <div className="row ">
                <p>
                    Ever wanted to see a movie but didn’t know which of the many
                    streaming platforms actually has it? Look no more! STRM
                    FINDR is the place for you!
                </p>
                <p>
                    We're a team of junior developers in our last module in
                    Codecool. We've been working together for roughly six
                    months.
                </p>
                <p />
                <br />
                <br />
                <div className="col-sm">
                    <div className="card">
                        <h3>Diana Gradinaru</h3>
                        <a href="https://github.com/DianaGradinaru">
                            <VscGithubInverted className="git" />
                        </a>
                    </div>
                </div>
                <div className="col-sm">
                    <div className="card">
                        <h3>Madalina Grama</h3>
                        <a href="https://github.com/madalinagrama">
                            <VscGithubInverted className="git" />
                        </a>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default About;

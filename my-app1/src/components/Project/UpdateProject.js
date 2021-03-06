import React, { Component } from "react";
import { getProject } from "../../action/projectActions";
import { createProject } from "../../action/projectActions";
import PropTypes from "prop-types";
import { connect } from "react-redux";

class UpdateProject extends Component {
  //set state
  constructor() {
    super();

    this.state = {
      id: "",
      projectName: "",
      projectIdentifier: "",
      description: "",
      start_date: "",
      end_date: "",
    };
    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }
  componentWillReceiveProps(nextProps) {
    const {
      id,
      projectName,
      projectIdentifier,
      description,
      start_date,
      end_date,
    } = nextProps.project;

    this.setState({
      id,
      projectName,
      projectIdentifier,
      description,
      start_date,
      end_date,
    });
  }
  componentDidMount() {
    const { id } = this.props.match.params;
    this.props.getProject(id, this.props.history);
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }
  onSubmit(e) {
    e.preventDefault();

    const updateProject = {
      id: this.state.id,
      projectName: this.state.projectName,
      projectIdentifier: this.state.projectIdentifier,
      description: this.state.description,
      start_date: this.state.start_date,
      end_date: this.state.end_date,
    };

    this.props.createProject(updateProject, this.props.history);
  }
  render() {
    return (
      <div class="register">
        <div class="container">
          <div class="row">
            <div class="col-md-8 m-auto">
              <h5 class="display-4 text-center">Update Project form</h5>
              <hr />
              <form onSubmit={this.onSubmit}>
                <div class="form-group">
                  <input
                    type="text"
                    class="form-control form-control-lg "
                    placeholder="Project Name"
                    name="projectName"
                    value={this.state.projectName}
                    onChange={this.onChange}
                  />
                </div>
                <div class="form-group">
                  <input
                    type="text"
                    class="form-control form-control-lg"
                    placeholder="Unique Project ID"
                    name="projectIdentifier"
                    value={this.state.projectIdentifier}
                    onChange={this.onChange}
                  />
                </div>

                <div class="form-group">
                  <textarea
                    class="form-control form-control-lg"
                    placeholder="Project Description"
                    name="description"
                    value={this.state.description}
                    onChange={this.onChange}
                  ></textarea>
                </div>
                <h6>Start Date</h6>
                <div class="form-group">
                  <input
                    type="date"
                    class="form-control form-control-lg"
                    name="start_date"
                    value={this.state.start_date}
                    onChange={this.onChange}
                  />
                </div>
                <h6>Estimated End Date</h6>
                <div class="form-group">
                  <input
                    type="date"
                    class="form-control form-control-lg"
                    name="end_date"
                    value={this.state.end_date}
                    onChange={this.onChange}
                  />
                </div>

                <input type="submit" class="btn btn-primary btn-block mt-4" />
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
UpdateProject.propTypes = {
  getProject: PropTypes.func.isRequired,
  createProject: PropTypes.func.isRequired,
  project: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  project: state.project.project,
});
export default connect(mapStateToProps, { getProject, createProject })(
  UpdateProject
);

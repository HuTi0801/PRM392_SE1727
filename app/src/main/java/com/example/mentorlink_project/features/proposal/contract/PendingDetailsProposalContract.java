package com.example.mentorlink_project.features.proposal.contract;

import com.example.mentorlink_project.data.entities.ProjectEntity;

import java.util.List;

public interface PendingDetailsProposalContract {
    interface View {
        void showProposals(List<ProjectEntity> proposalList);
        void showRejectDialog(ProjectEntity project);
        void showMessage(String message);
    }

    interface Presenter {
        void loadPendingProposals();
        void approveProposal(ProjectEntity project);
        void rejectProposal(ProjectEntity project, String reason);
    }
}

package com.example.mentorlink_project.features.proposal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.mentorlink_project.R;
import com.example.mentorlink_project.data.entities.ProjectEntity;
import com.example.mentorlink_project.features.proposal.contract.PendingDetailsProposalContract;

import java.util.List;

public class PendingDetailProposalAdapter extends BaseAdapter {
    private final Context context;
    private final List<ProjectEntity> proposalList;
    private final PendingDetailsProposalContract.Presenter presenter;

    public PendingDetailProposalAdapter(Context context, List<ProjectEntity> proposalList, PendingDetailsProposalContract.Presenter presenter) {
        this.context = context;
        this.proposalList = proposalList;
        this.presenter = presenter;
    }

    @Override
    public int getCount() {
        return proposalList.size();
    }

    @Override
    public Object getItem(int position) {
        return proposalList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return proposalList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProjectEntity project = proposalList.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_list_item_proposal, parent, false);
        }

        TextView tvTitle = convertView.findViewById(R.id.tvProposalTitle);
        Button btnApprove = convertView.findViewById(R.id.btnApprove);
        Button btnReject = convertView.findViewById(R.id.btnReject);

        tvTitle.setText(project.getTopic());

        btnApprove.setOnClickListener(v -> presenter.approveProposal(project));

        btnReject.setOnClickListener(v -> {
            if (context instanceof PendingDetailsProposalContract.View) {
                ((PendingDetailsProposalContract.View) context).showRejectDialog(project);
            }
        });

        // (Tuỳ chọn) Mở chi tiết đề tài khi click item
        convertView.setOnClickListener(v -> {
            // TODO: mở ProposalDetailActivity nếu muốn
            // Intent intent = new Intent(context, ProposalDetailActivity.class);
            // intent.putExtra("project_id", project.getId());
            // context.startActivity(intent);
        });

        return convertView;
    }
}
